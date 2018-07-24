package ale.xtext.ui.menus

import ale.xtext.generator.RevisitorInterfaceGenerator
import ale.xtext.utils.EcoreUtils
import ale.xtext.utils.NamingUtils
import java.io.FileWriter
import java.io.IOException
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.IAdaptable
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.core.runtime.Path
import org.eclipse.jface.action.IAction
import org.eclipse.jface.dialogs.MessageDialog
import org.eclipse.jface.viewers.ISelection
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.swt.widgets.Shell
import org.eclipse.ui.IObjectActionDelegate
import org.eclipse.ui.IWorkbenchPart

class GenerateRevisitorInterface implements IObjectActionDelegate {
	extension EcoreUtils = new EcoreUtils()
	extension NamingUtils = new NamingUtils()
	RevisitorInterfaceGenerator generator = new RevisitorInterfaceGenerator()
	Shell shell
	IFile selectedIFile

	override void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.site.shell
	}

	override void run(IAction action) {
		val ecorePath = selectedIFile.fullPath.toString
		val pkg = loadEPackage(ecorePath)
		val gm = loadCorrespondingGenmodel(ecorePath)

		if (pkg === null) {
			MessageDialog.openError(shell, "Error", "Cannot find EPackage for " + ecorePath);
			return
		}

		if (gm === null) {
			MessageDialog.openError(shell, "Error", "Cannot find genmodel for " + ecorePath);
			return
		}

		val project = selectedIFile.project
		val path = project.location.append(new Path(pkg.revisitorInterfacePath))
		path.toFile().mkdirs()
		val file = path.append(new Path(pkg.revisitorInterfaceName)).addFileExtension("java")

		val content = generator.generateInterface(pkg, gm)

		try {
			val fileWriter = new FileWriter(file.toFile())
			fileWriter.write(content)
			fileWriter.close()
			project.refreshLocal(IResource::DEPTH_INFINITE, new NullProgressMonitor())
		} catch (IOException e) {
			e.printStackTrace()
		} catch (CoreException e) {
			e.printStackTrace()
		}
	}

	override void selectionChanged(IAction action, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			val o = selection.firstElement
			if (o instanceof IFile)
				selectedIFile = o
			if (o instanceof IAdaptable) {
				val res = o.getAdapter(typeof(IFile))
				if (res !== null)
					selectedIFile = res
			}
		}
	}
}
