package co.edu.uniquindio.poo.taller.controller;

import co.edu.uniquindio.poo.taller.model.CategoriaLibro;
import co.edu.uniquindio.poo.taller.model.ConfiguracionBiblioteca;
import co.edu.uniquindio.poo.taller.model.EstadoLibro;
import co.edu.uniquindio.poo.taller.model.Libro;
import co.edu.uniquindio.poo.taller.model.Prestamo;
import co.edu.uniquindio.poo.taller.model.ServicioBiblioteca;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class BibliotecaController {
    private final ServicioBiblioteca servicioBiblioteca = new ServicioBiblioteca();
    private final ObservableList<Libro> libros = FXCollections.observableArrayList();
    private final ObservableList<Prestamo> prestamos = FXCollections.observableArrayList();

    @FXML
    private TextField nombreBibliotecaField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField porcentajeMultaField;
    @FXML
    private TextField codigoField;
    @FXML
    private TextField tituloField;
    @FXML
    private TextField autorField;
    @FXML
    private ComboBox<CategoriaLibro> categoriaCombo;
    @FXML
    private TextField codigoBaseField;
    @FXML
    private TextField nuevoCodigoField;
    @FXML
    private TextField codigoPrestamoField;
    @FXML
    private TextField diasPrestamoField;
    @FXML
    private TextField codigoDevolucionField;
    @FXML
    private TableView<Libro> librosTable;
    @FXML
    private TableColumn<Libro, String> codigoColumn;
    @FXML
    private TableColumn<Libro, String> tituloColumn;
    @FXML
    private TableColumn<Libro, String> autorColumn;
    @FXML
    private TableColumn<Libro, CategoriaLibro> categoriaColumn;
    @FXML
    private TableColumn<Libro, EstadoLibro> estadoColumn;
    @FXML
    private TableView<Prestamo> prestamosTable;
    @FXML
    private TableColumn<Prestamo, String> libroPrestamoColumn;
    @FXML
    private TableColumn<Prestamo, LocalDate> fechaPrestamoColumn;
    @FXML
    private TableColumn<Prestamo, LocalDate> fechaLimiteColumn;
    @FXML
    private TableColumn<Prestamo, LocalDate> fechaDevolucionColumn;
    @FXML
    private TableColumn<Prestamo, String> estadoPrestamoColumn;
    @FXML
    private Label mensajeLabel;

    @FXML
    /** Inicializa los controles y carga la informacion inicial en la vista. */
    public void initialize() {
        configurarFormulario();
        configurarTablas();
        refrescarTablas();
    }

    @FXML
    /** Lee y guarda desde el formulario la configuracion Singleton de la biblioteca. */
    private void guardarConfiguracion() {
        try {
            double porcentajeMulta = Double.parseDouble(porcentajeMultaField.getText().trim());
            ConfiguracionBiblioteca.getInstancia()
                    .actualizar(nombreBibliotecaField.getText(), direccionField.getText(), porcentajeMulta);
            mostrarMensaje("Configuracion actualizada correctamente");
        } catch (RuntimeException exception) {
            mostrarError(exception.getMessage());
        }
    }

    @FXML
    /** Construye y registra un libro a partir de los campos de la interfaz. */
    private void registrarLibro() {
        try {
            Libro libro = Libro.builder(codigoField.getText(), tituloField.getText(), autorField.getText())
                    .categoria(categoriaCombo.getValue())
                    .build();
            servicioBiblioteca.registrarLibro(libro);
            limpiarFormularioLibro();
            refrescarTablas();
            mostrarMensaje("Libro registrado correctamente");
        } catch (RuntimeException exception) {
            mostrarError(exception.getMessage());
        }
    }

    @FXML
    /** Solicita al servicio una copia del libro seleccionado mediante sus codigos. */
    private void clonarLibro() {
        try {
            servicioBiblioteca.clonarLibro(codigoBaseField.getText(), nuevoCodigoField.getText());
            codigoBaseField.clear();
            nuevoCodigoField.clear();
            refrescarTablas();
            mostrarMensaje("Libro clonado correctamente");
        } catch (RuntimeException exception) {
            mostrarError(exception.getMessage());
        }
    }

    @FXML
    /** Registra un prestamo usando el codigo del libro y los dias indicados. */
    private void prestarLibro() {
        try {
            int diasPrestamo = Integer.parseInt(diasPrestamoField.getText().trim());
            servicioBiblioteca.prestarLibro(codigoPrestamoField.getText(), diasPrestamo);
            codigoPrestamoField.clear();
            refrescarTablas();
            mostrarMensaje("Prestamo registrado correctamente");
        } catch (RuntimeException exception) {
            mostrarError(exception.getMessage());
        }
    }

    @FXML
    /** Registra una devolucion y muestra la multa resultante si existe. */
    private void devolverLibro() {
        try {
            Prestamo prestamo = servicioBiblioteca.devolverLibro(codigoDevolucionField.getText());
            codigoDevolucionField.clear();
            refrescarTablas();
            mostrarMensaje("Libro devuelto. Multa calculada: " + prestamo.calcularMulta());
        } catch (RuntimeException exception) {
            mostrarError(exception.getMessage());
        }
    }

    @FXML
    /** Restablece los campos del formulario de registro de libros. */
    private void limpiarFormularioLibro() {
        codigoField.clear();
        tituloField.clear();
        autorField.clear();
        categoriaCombo.setValue(CategoriaLibro.INGENIERIA);
    }

    /** Carga valores iniciales y opciones de los controles del formulario. */
    private void configurarFormulario() {
        ConfiguracionBiblioteca configuracion = ConfiguracionBiblioteca.getInstancia();
        nombreBibliotecaField.setText(configuracion.getNombre());
        direccionField.setText(configuracion.getDireccion());
        porcentajeMultaField.setText(String.valueOf(configuracion.getPorcentajeMulta()));
        categoriaCombo.setItems(FXCollections.observableArrayList(CategoriaLibro.values()));
        categoriaCombo.setValue(CategoriaLibro.INGENIERIA);
        diasPrestamoField.setText("7");
    }

    /** Vincula las columnas de las tablas con las propiedades del modelo. */
    private void configurarTablas() {
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tituloColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        autorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        categoriaColumn.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        estadoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        libroPrestamoColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getLibro().getCodigo()));
        fechaPrestamoColumn.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        fechaLimiteColumn.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));
        fechaDevolucionColumn.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        estadoPrestamoColumn.setCellValueFactory(new PropertyValueFactory<>("estado"));

        librosTable.setItems(libros);
        prestamosTable.setItems(prestamos);
    }

    /** Sincroniza las tablas observables con los datos del servicio. */
    private void refrescarTablas() {
        libros.setAll(servicioBiblioteca.obtenerLibros());
        prestamos.setAll(servicioBiblioteca.obtenerPrestamos());
        librosTable.refresh();
        prestamosTable.refresh();
    }

    /** Muestra un mensaje de operacion exitosa en color verde. */
    private void mostrarMensaje(String mensaje) {
        mensajeLabel.setText(mensaje);
        mensajeLabel.setStyle("-fx-text-fill: #166534;");
    }

    /** Muestra un mensaje de error en color rojo. */
    private void mostrarError(String mensaje) {
        mensajeLabel.setText(mensaje);
        mensajeLabel.setStyle("-fx-text-fill: #b91c1c;");
    }
}
