import 'date-fns';
import React from 'react';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import {MuiPickersUtilsProvider, KeyboardDatePicker,} from '@material-ui/pickers';

export default function MaterialUIPickers(props) {
    const [selectedDate, setSelectedDate] = React.useState(props.expiration.get(props.ingredient));

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
                    // margin="normal"
                    // id="date-picker-inline"
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
