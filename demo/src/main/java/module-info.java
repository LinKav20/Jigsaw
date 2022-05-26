module com.flinkou.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.flinkou.demo to javafx.fxml;
    exports com.flinkou.demo;
    exports com.flinkou.demo.core.controllers;
    opens com.flinkou.demo.core.controllers to javafx.fxml;
    exports com.flinkou.demo.props;
    opens com.flinkou.demo.props to javafx.fxml;
    exports com.flinkou.demo.ui.models;
    opens com.flinkou.demo.ui.models to javafx.fxml;
    exports com.flinkou.demo.core.models;
    opens com.flinkou.demo.core.models to javafx.fxml;
    exports com.flinkou.demo.core.generation;
    opens com.flinkou.demo.core.generation to javafx.fxml;
}