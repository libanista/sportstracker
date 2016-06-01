package de.saring.sportstracker.gui.views.listviews;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import de.saring.sportstracker.gui.views.ViewPrinter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.saring.sportstracker.core.STOptions;
import de.saring.sportstracker.data.Equipment;
import de.saring.sportstracker.data.Exercise;
import de.saring.sportstracker.data.SportSubType;
import de.saring.sportstracker.data.SportType;
import de.saring.sportstracker.gui.STContext;
import de.saring.sportstracker.gui.STDocument;
import de.saring.util.StringUtils;
import de.saring.util.data.IdObject;
import de.saring.util.gui.javafx.ColorUtils;
import de.saring.util.gui.javafx.FormattedNumberCellFactory;
import de.saring.util.gui.javafx.LocalDateCellFactory;

/**
 * Controller class of the Exercise List View, which displays all the user exercises
 * (or a filtered list) in a table view.
 *
 * @author Stefan Saring
 */
@Singleton
public class ExerciseListViewController extends AbstractListViewController<Exercise> {

    @FXML
    private TableView<Exercise> tvExercises;

    @FXML
    private TableColumn<Exercise, LocalDateTime> tcDate;
    @FXML
    private TableColumn<Exercise, Object> tcSportType;
    @FXML
    private TableColumn<Exercise, Object> tcSportSubtype;
    @FXML
    private TableColumn<Exercise, Number> tcDuration;
    @FXML
    private TableColumn<Exercise, Object> tcIntensity;
    @FXML
    private TableColumn<Exercise, Number> tcDistance;
    @FXML
    private TableColumn<Exercise, Number> tcAvgSpeed;
    @FXML
    private TableColumn<Exercise, Number> tcAvgHeartrate;
    @FXML
    private TableColumn<Exercise, Number> tcAscent;
    @FXML
    private TableColumn<Exercise, Number> tcEnergy;
    @FXML
    private TableColumn<Exercise, Object> tcEquipment;
    @FXML
    private TableColumn<Exercise, Object> tcComment;

    /**
     * Standard c'tor for dependency injection.
     *
     * @param context the SportsTracker UI context
     * @param document the SportsTracker document / model
     * @param viewPrinter the printer of the SportsTracker views
     */
    @Inject
    public ExerciseListViewController(final STContext context, final STDocument document, final ViewPrinter viewPrinter) {
        super(context, document, viewPrinter);
    }

    @Override
    public ViewType getViewType() {
        return ViewType.EXERCISE_LIST;
    }

    @Override
    public int getSelectedExerciseCount() {
        return getSelectedEntryCount();
    }

    @Override
    public int[] getSelectedExerciseIDs() {
        return getSelectedEntryIDs();
    }

    @Override
    public void selectEntry(final IdObject entry) {
        if (entry != null && entry instanceof Exercise) {
            selectAndScrollToEntry((Exercise) entry);
        }
    }

    @Override
    protected String getFxmlFilename() {
        return "/fxml/views/ExerciseListView.fxml";
    }

    @Override
    protected TableView<Exercise> getTableView() {
        return tvExercises;
    }

    @Override
    protected void setupTableColumns() {

        // setup factories for providing cell values
        tcDate.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
        tcSportType.setCellValueFactory(cellData -> {
            final SportType sportType = cellData.getValue().getSportType();
            return new SimpleObjectProperty<>(sportType == null ? null : sportType.getName());
        });
        tcSportSubtype.setCellValueFactory(cellData -> {
            final SportSubType sportSubType = cellData.getValue().getSportSubType();
            return new SimpleObjectProperty<>(sportSubType == null ? null : sportSubType.getName());
        });
        tcDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        tcIntensity.setCellValueFactory(new PropertyValueFactory<>("intensity"));
        tcDistance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        tcAvgSpeed.setCellValueFactory(new PropertyValueFactory<>("avgSpeed"));
        tcAvgHeartrate.setCellValueFactory(new PropertyValueFactory<>("avgHeartRate"));
        tcAscent.setCellValueFactory(new PropertyValueFactory<>("ascent"));
        tcEnergy.setCellValueFactory(new PropertyValueFactory<>("calories"));
        tcEquipment.setCellValueFactory(cellData -> {
            final Equipment equipment = cellData.getValue().getEquipment();
            return new SimpleObjectProperty<>(equipment == null ? null : equipment.getName());
        });
        tcEquipment.setCellValueFactory(cellData -> new SimpleObjectProperty<>( //
                cellData.getValue().getEquipment() == null ? null : cellData.getValue().getEquipment().getName()));
        tcComment.setCellValueFactory(cellData -> new SimpleObjectProperty<>( //
                StringUtils.getFirstLineOfText(cellData.getValue().getComment())));

        // setup custom factories for displaying cells
        tcDate.setCellFactory(new LocalDateCellFactory());
        tcDuration.setCellFactory(new FormattedNumberCellFactory<>(value -> //
                value == null ? null : getContext().getFormatUtils().seconds2TimeString(value.intValue())));
        tcDistance.setCellFactory(new FormattedNumberCellFactory<>(value -> //
                value == null ? null : getContext().getFormatUtils().distanceToString(value.doubleValue(), 3)));
        tcAvgSpeed.setCellFactory(new FormattedNumberCellFactory<>(value -> //
                value == null ? null : getContext().getFormatUtils().speedToString(value.floatValue(), 2)));
        tcAvgHeartrate.setCellFactory(new FormattedNumberCellFactory<>(value -> //
                value == null ? null : getContext().getFormatUtils().heartRateToString(value.intValue())));
        tcAscent.setCellFactory(new FormattedNumberCellFactory<>(value -> //
                value == null ? null : getContext().getFormatUtils().heightToString(value.intValue())));
        tcEnergy.setCellFactory(new FormattedNumberCellFactory<>(value -> //
                value == null ? null : getContext().getFormatUtils().caloriesToString(value.intValue())));

        // set initial visibility of optional columns as configured in preferences
        final STOptions options = getDocument().getOptions();
        tcAvgHeartrate.setVisible(options.isListViewShowAvgHeartrate());
        tcAscent.setVisible(options.isListViewShowAscent());
        tcEnergy.setVisible(options.isListViewShowEnergy());
        tcEquipment.setVisible(options.isListViewShowEquipment());
        tcComment.setVisible(options.isListViewShowComment());
    }

    @Override
    protected void setupDefaultSorting() {
        // default sort order is by date descending
        tcDate.setSortType(TableColumn.SortType.DESCENDING);
        tvExercises.getSortOrder().addAll(tcDate);
    }

    @Override
    protected List<Exercise> getTableEntries() {
        return getDocument().getFilterableExerciseList().stream().collect(Collectors.toList());
    }

    @Override
    protected void updateTableRowColor(final TableRow<Exercise> tableRow) {

        // use text color of sport type for the table row (or white when the row is selected and the table is focused)
        // => tableRow.setTextFill() does not work here, color must be set by a CSS style
        final Exercise exercise = tableRow.getItem();
        if (exercise != null) {
            final boolean useDefaultColor = tableRow.isSelected() && tvExercises.isFocused();
            final String color = useDefaultColor ? "white" : ColorUtils.toRGBCode(exercise.getSportType().getColor());
            tableRow.setStyle("-fx-text-background-color: " + color + ";");
        }
    }
}
