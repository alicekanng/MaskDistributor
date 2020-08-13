package ui;

import ui.tabs.ForeignListTab;
import ui.tabs.HomeTab;
import ui.tabs.LocalListTab;

import javax.swing.*;

// Sets up the GUI frame with all its tabs
public class DistributionListUI extends JFrame {
    private HomeTab homeTab;
    private LocalListTab localTab;
    private ForeignListTab foreignTab;
    private Application app;

    public static final int HOME_TAB_INDEX = 0;
    public static final int LOCAL_TAB_INDEX = 1;
    public static final int FOREIGN_TAB_INDEX = 2;

    private static final int WIDTH = 600;
    private static final int HEIGHT = 300;

    private JTabbedPane sidebar;

    public DistributionListUI() {
        super("Mask Distributor Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: loads the tabs of the application on the side bar
    private void loadTabs() {
        homeTab = new HomeTab(this);
        localTab = new LocalListTab();
        foreignTab = new ForeignListTab();

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(localTab, LOCAL_TAB_INDEX);
        sidebar.setTitleAt(LOCAL_TAB_INDEX, "Local List");
        sidebar.add(foreignTab, FOREIGN_TAB_INDEX);
        sidebar.setTitleAt(FOREIGN_TAB_INDEX, "Foreign List");
    }

    public LocalListTab getLocalTab() {
        return localTab;
    }

    public ForeignListTab getForeignTab() {
        return foreignTab;
    }

    public JTabbedPane getSidebar() {
        return sidebar;
    }
}
