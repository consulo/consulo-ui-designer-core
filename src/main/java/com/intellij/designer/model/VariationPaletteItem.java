package com.intellij.designer.model;

import com.intellij.designer.palette.PaletteItem;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.text.StringUtil;
import consulo.ui.image.Image;
import org.jdom.Element;

import javax.annotation.Nullable;

/**
 * Implementation of a {@link PaletteItem} which delegates to another {@linkplain PaletteItem}
 * but which possibly overrides the title, icon and or creation properties.
 */
final class VariationPaletteItem implements PaletteItem {
  private final PaletteItem myDefaultItem;
  private final MetaModel myModel;
  private final String myTitle;
  private final String myIconPath;
  private final String myTooltip;
  private final String myCreation;
  private Image myIcon;

  VariationPaletteItem(PaletteItem defaultItem, MetaModel model, Element element) {
    myDefaultItem = defaultItem;
    myModel = model;

    String title = element.getAttributeValue("title");
    if (StringUtil.isEmpty(title)) {
      title = myDefaultItem.getTitle();
    }
    myTitle = title;

    String iconPath = element.getAttributeValue("icon");
    if (StringUtil.isEmpty(iconPath)) {
      myIcon = myDefaultItem.getIcon();
    }
    myIconPath = iconPath;

    String tooltip = element.getAttributeValue("tooltip");
    if (StringUtil.isEmpty(tooltip)) {
      tooltip = myDefaultItem.getTooltip();
    }
    myTooltip = tooltip;

    Element creation = element.getChild("creation");
    if (creation != null) {
      myCreation = creation.getTextTrim();
    }
    else {
      myCreation = myModel.getCreation();
    }
  }

  @Override
  public String getTitle() {
    return myTitle;
  }

  @Override
  public Image getIcon() {
    if (myIcon == null) {
      assert myIconPath != null;
      myIcon = IconLoader.findIcon(myIconPath, myModel.getModel());
    }
    return myIcon;
  }

  @Override
  public String getTooltip() {
    return myTooltip;
  }

  @Override
  public String getVersion() {
    return myDefaultItem.getVersion();
  }

  @Override
  public boolean isEnabled() {
    return myDefaultItem.isEnabled();
  }

  @Override
  public String getCreation() {
    return myCreation;
  }

  @Override
  public MetaModel getMetaModel() {
    return myModel;
  }

  @Nullable
  @Override
  public String getDeprecatedIn() {
    return myDefaultItem.getDeprecatedIn();
  }

  @Nullable
  @Override
  public String getDeprecatedHint() {
    return myDefaultItem.getDeprecatedHint();
  }
}