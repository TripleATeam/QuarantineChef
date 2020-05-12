import 'date-fns';
import React from 'react';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import {MuiPickersUtilsProvider, KeyboardDatePicker,} from '@material-ui/pickers';

// this function supplies a datepicker object for each ingredient
export default function ExpirationDatePicker(props) {
    const [selectedDate, setSelectedDate] = React.useState(props.expiration.get(props.ingredient));

    // update the date view and the expiration map for database update
    const handleDateChange = (date) => {
        setSelectedDate(date);
        props.expiration.set(props.ingredient, date);
    };

    return (
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <Grid container justify="space-around">
                <KeyboardDatePicker
                    disableToolbar
                    variant="inline"
                    format="MM/dd/yyyy"
                    label="Expiration Date (optional)"
                    value={selectedDate}
                    onChange={handleDateChange}
                    KeyboardButtonProps={{
                        'aria-label': 'change date',
                    }}
                />
            </Grid>
        </MuiPickersUtilsProvider>
    );
}
