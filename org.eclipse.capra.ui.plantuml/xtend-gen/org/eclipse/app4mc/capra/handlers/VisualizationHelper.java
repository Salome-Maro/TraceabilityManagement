/*******************************************************************************
 *  Copyright (c) 2016 Chalmers | Gothenburg University, rt-labs and others.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *  
 *   Contributors:
 *      Chalmers|Gothenburg University and rt-labs - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.app4mc.capra.handlers;

import com.google.common.base.Objects;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.eclipse.app4mc.capra.core.adapters.Connection;
import org.eclipse.app4mc.capra.core.adapters.TraceMetamodelAdapter;
import org.eclipse.app4mc.capra.core.helpers.ExtensionPointHelper;
import org.eclipse.app4mc.capra.generic.helpers.EMFHelper;
import org.eclipse.app4mc.capra.handlers.Connections;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class VisualizationHelper {
  public static String createMatrix(final EObject traceModel, final Collection<EObject> firstElements, final Collection<EObject> secondElements) {
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
      _builder.newLine();
      _builder.append("@enduml");
      _builder.newLine();
      _xblockexpression = _builder.toString();
    }
    return _xblockexpression;
  }
  
  public static String createNeighboursView(final List<Connection> connections, final EObject selectedObject) {
    String _xblockexpression = null;
    {
      Connections helper = new Connections(connections, selectedObject);
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@startuml");
      _builder.newLine();
      _builder.append("object \"");
      String _originLabel = helper.originLabel();
      _builder.append(_originLabel, "");
      _builder.append("\" as ");
      String _originId = helper.originId();
      _builder.append(_originId, "");
      _builder.append(" #pink");
      _builder.newLineIfNotEmpty();
      {
        Collection<String> _objectIdsWithoutOrigin = helper.objectIdsWithoutOrigin();
        for(final String id : _objectIdsWithoutOrigin) {
          _builder.append("object \"");
          String _label = helper.label(id);
          _builder.append(_label, "");
          _builder.append("\" as ");
          _builder.append(id, "");
          _builder.newLineIfNotEmpty();
        }
      }
      {
        List<String> _arrows = helper.arrows();
        for(final String a : _arrows) {
          _builder.append(a, "");
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
