package ale.xtext.ui.menus;

import ale.xtext.generator.RevisitorInterfaceGenerator;
import ale.xtext.utils.EcoreUtils;
import ale.xtext.utils.NamingUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class GenerateRevisitorInterface implements IObjectActionDelegate {
  @Extension
  private EcoreUtils _ecoreUtils = new EcoreUtils();
  
  @Extension
  private NamingUtils _namingUtils = new NamingUtils();
  
  private RevisitorInterfaceGenerator generator = new RevisitorInterfaceGenerator();
  
  private Shell shell;
  
  private IFile selectedIFile;
  
  @Override
  public void setActivePart(final IAction action, final IWorkbenchPart targetPart) {
    this.shell = targetPart.getSite().getShell();
  }
  
  @Override
  public void run(final IAction action) {
    final String ecorePath = this.selectedIFile.getFullPath().toString();
    final EPackage pkg = this._ecoreUtils.loadEPackage(ecorePath);
    final GenModel gm = this._ecoreUtils.loadCorrespondingGenmodel(ecorePath);
    if ((pkg == null)) {
      MessageDialog.openError(this.shell, "Error", ("Cannot find EPackage for " + ecorePath));
      return;
    }
    if ((gm == null)) {
      MessageDialog.openError(this.shell, "Error", ("Cannot find genmodel for " + ecorePath));
      return;
    }
    final IProject project = this.selectedIFile.getProject();
    IPath _location = project.getLocation();
    String _revisitorInterfacePath = this._namingUtils.getRevisitorInterfacePath(pkg);
    Path _path = new Path(_revisitorInterfacePath);
    final IPath path = _location.append(_path);
    path.toFile().mkdirs();
    String _revisitorInterfaceName = this._namingUtils.getRevisitorInterfaceName(pkg);
    Path _path_1 = new Path(_revisitorInterfaceName);
    final IPath file = path.append(_path_1).addFileExtension("java");
    final String content = this.generator.generateInterface(pkg, gm);
    try {
      File _file = file.toFile();
      final FileWriter fileWriter = new FileWriter(_file);
      fileWriter.write(content);
      fileWriter.close();
      NullProgressMonitor _nullProgressMonitor = new NullProgressMonitor();
      project.refreshLocal(IResource.DEPTH_INFINITE, _nullProgressMonitor);
    } catch (final Throwable _t) {
      if (_t instanceof IOException) {
        final IOException e = (IOException)_t;
        e.printStackTrace();
      } else if (_t instanceof CoreException) {
        final CoreException e_1 = (CoreException)_t;
        e_1.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  @Override
  public void selectionChanged(final IAction action, final ISelection selection) {
    if ((selection instanceof IStructuredSelection)) {
      final Object o = ((IStructuredSelection)selection).getFirstElement();
      if ((o instanceof IFile)) {
        this.selectedIFile = ((IFile)o);
      }
      if ((o instanceof IAdaptable)) {
        final IFile res = ((IAdaptable)o).<IFile>getAdapter(IFile.class);
        if ((res != null)) {
          this.selectedIFile = res;
        }
      }
    }
  }
}
