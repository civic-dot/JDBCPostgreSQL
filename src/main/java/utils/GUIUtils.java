package utils;


import org.jdatepicker.impl.*;


import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class GUIUtils {
    public static JDatePickerImpl createDatePicker() {
        SqlDateModel model = new SqlDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        return new JDatePickerImpl(datePanel, new LabelDateFormatter());
    }

    static class LabelDateFormatter extends JFormattedTextField.AbstractFormatter {
        private String datePatern = "dd/MM/yyyy";

        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePatern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }
    }

}
