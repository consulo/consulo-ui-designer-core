/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.designer.designSurface;

import java.awt.Cursor;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;
import javax.swing.JComponent;

import javax.annotation.Nullable;
import com.intellij.designer.designSurface.tools.InputTool;
import com.intellij.designer.model.RadComponent;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.util.Key;

/**
 * @author Alexander Lobas
 */
public interface EditableArea {
  Key<EditableArea> DATA_KEY = Key.create("EditableArea");

  //////////////////////////////////////////////////////////////////////////////////////////
  //
  // Selection
  //
  //////////////////////////////////////////////////////////////////////////////////////////

  void addSelectionListener(ComponentSelectionListener listener);

  void removeSelectionListener(ComponentSelectionListener listener);

  @Nonnull
  List<RadComponent> getSelection();

  boolean isSelected(@Nonnull RadComponent component);

  void select(@Nonnull RadComponent component);

  void deselect(@Nonnull RadComponent component);

  void appendSelection(@Nonnull RadComponent component);

  void setSelection(@Nonnull List<RadComponent> components);

  void deselect(@Nonnull Collection<RadComponent> components);

  void deselectAll();

  void scrollToSelection();

  //////////////////////////////////////////////////////////////////////////////////////////
  //
  // Visual
  //
  //////////////////////////////////////////////////////////////////////////////////////////

  void setCursor(@Nullable Cursor cursor);

  void setDescription(@Nullable String text);

  @Nonnull
  JComponent getNativeComponent();

  @Nullable
  RadComponent findTarget(int x, int y, @Nullable ComponentTargetFilter filter);

  @Nullable
  InputTool findTargetTool(int x, int y);

  void showSelection(boolean value);

  ComponentDecorator getRootSelectionDecorator();

  @Nullable
  EditOperation processRootOperation(OperationContext context);

  FeedbackLayer getFeedbackLayer();

  RadComponent getRootComponent();

  boolean isTree();

  @Nullable
  FeedbackTreeLayer getFeedbackTreeLayer();

  ActionGroup getPopupActions();

  String getPopupPlace();
}