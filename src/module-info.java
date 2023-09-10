module LinkMind {
	requires javafx.controls;
    requires javafx.swing;
    requires javafx.graphics;
	opens application to javafx.graphics, javafx.fxml;
}
