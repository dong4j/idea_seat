package icons;

import com.intellij.openapi.util.IconLoader;

import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

/**
 * <p>Description: {@link com.intellij.icons.AllIcons}</p>
 *
 * @author dong4j
 * @version 1.0.0
 * @email "mailto:dong4j@gmail.com"
 * @date 2021.03.09 13:12
 * @since 1.2.0
 */
public class PluginIcons {
    private static final String ICON_FOLDER = "/icons/";

    /**
     * Load
     *
     * @param iconFilename icon filename
     * @return the icon
     * @since 1.0.0
     */
    @NotNull
    private static Icon load(String iconFilename) {
        return IconLoader.getIcon(ICON_FOLDER + iconFilename, PluginIcons.class);
    }
}
