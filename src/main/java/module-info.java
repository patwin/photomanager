module photomanager {

    requires java.desktop;
    requires javafx.controls;
    requires javafx.graphics;

    requires metadata.extractor;
    requires xmpcore;

    opens photomanager.ui to javafx.graphics;
}
