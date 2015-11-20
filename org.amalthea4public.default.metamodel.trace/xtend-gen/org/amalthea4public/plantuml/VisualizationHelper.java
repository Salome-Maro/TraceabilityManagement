package org.amalthea4public.plantuml;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.amalthea4public.tracemanagement.generic.adapters.TraceMetamodelAdapter;
import org.amalthea4public.tracemanagement.generic.helpers.EMFHelper;
import org.amalthea4public.tracemanagement.generic.helpers.ExtensionPointHelper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class VisualizationHelper {
  public static String createMatrix(final Optional<EObject> traceModel, final Collection<EObject> firstElements, final Collection<EObject> secondElements) {
    String _xblockexpression = null;
    {
      Optional<TraceMetamodelAdapter> _traceMetamodelAdapter = ExtensionPointHelper.getTraceMetamodelAdapter();
      final TraceMetamodelAdapter traceAdapter = _traceMetamodelAdapter.get();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@startuml");
      _builder.newLine();
      _builder.append("salt");
      _builder.newLine();
      _builder.append("{#");
      _builder.newLine();
      {
        boolean _notEquals = (!Objects.equal(firstElements, null));
        if (_notEquals) {
          _builder.append(".");
          {
            for(final EObject e : secondElements) {
              _builder.append("|");
              String _identifier = EMFHelper.getIdentifier(e);
              _builder.append(_identifier, "");
            }
          }
          _builder.newLineIfNotEmpty();
          {
            for(final EObject first : firstElements) {
              String _identifier_1 = EMFHelper.getIdentifier(first);
              _builder.append(_identifier_1, "");
              _builder.append(" ");
              {
                for(final EObject second : secondElements) {
                  _builder.append("|");
                  {
                    boolean _isThereATraceBetween = traceAdapter.isThereATraceBetween(first, second, traceModel);
                    if (_isThereATraceBetween) {
                      _builder.append("X");
                    } else {
                      _builder.append(".");
                    }
                  }
                }
              }
              _builder.newLineIfNotEmpty();
            }
          }
        } else {
          _builder.append("Choose two containers to show a traceability matrix of their contents.");
          _builder.newLine();
        }
      }
      _builder.append("}");
      _builder.newLine();
      _builder.append("@enduml");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public static String createNeighboursView(final List<EObject> connectedElements, final List<String> traceLabels, final EObject selectedElement) {
    String _xblockexpression = null;
    {
      int i = 1;
      int j = 1;
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@startuml");
      _builder.newLine();
      _builder.append("object \"");
      String _identifier = EMFHelper.getIdentifier(selectedElement);
      _builder.append(_identifier, "");
      _builder.append("\" as o0 #pink");
      _builder.newLineIfNotEmpty();
      {
        for(final EObject e : connectedElements) {
          _builder.append("object \"");
          String _identifier_1 = EMFHelper.getIdentifier(e);
          _builder.append(_identifier_1, "");
          _builder.append("\" as o");
          int _plusPlus = i++;
          _builder.append(_plusPlus, "");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        for(final String t : traceLabels) {
          _builder.append(" o0 --> o");
          int _plusPlus_1 = j++;
          _builder.append(_plusPlus_1, "");
          _builder.append(" : \"");
          _builder.append(t, "");
          _builder.append("\"");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.append("@enduml");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
}
