package org.amalthea4public.generic.tracecreation.handlers;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.Optional;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceCreationHelper;
import org.amalthea4public.generic.tracecreation.metamodel.trace.adapter.TraceMetamodelAdapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MatrixHelper {
  public static String createMatrix(final Optional<EObject> traceModel, final Collection<EObject> firstElements, final Collection<EObject> secondElements) {
    String _xblockexpression = null;
    {
      Optional<TraceMetamodelAdapter> _traceMetamodelAdapter = TraceCreationHelper.getTraceMetamodelAdapter();
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
              String _identifier = TraceCreationHelper.getIdentifier(e);
              _builder.append(_identifier, "");
            }
          }
          _builder.newLineIfNotEmpty();
          {
            for(final EObject first : firstElements) {
              String _identifier_1 = TraceCreationHelper.getIdentifier(first);
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
}
