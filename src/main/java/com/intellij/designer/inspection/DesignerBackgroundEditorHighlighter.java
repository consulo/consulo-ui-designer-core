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
package com.intellij.designer.inspection;

import javax.annotation.Nonnull;

import com.intellij.codeHighlighting.BackgroundEditorHighlighter;
import com.intellij.codeHighlighting.HighlightingPass;
import com.intellij.designer.designSurface.DesignerEditorPanel;

/**
 * @author Alexander Lobas
 */
public final class DesignerBackgroundEditorHighlighter implements BackgroundEditorHighlighter {
  private final HighlightingPass[] myHighlightingPasses;

  public DesignerBackgroundEditorHighlighter(DesignerEditorPanel designer) {
    myHighlightingPasses = new HighlightingPass[]{new DesignerHighlightingPass(designer)};
  }

  @Nonnull
  @Override
  public HighlightingPass[] createPassesForEditor() {
    return myHighlightingPasses;
  }

  @Nonnull
  @Override
  public HighlightingPass[] createPassesForVisibleArea() {
    return HighlightingPass.EMPTY_ARRAY;
  }
}