import 'date-fns';
import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import DateFnsUtils from '@date-io/date-fns';
import {MuiPickersUtilsProvider, KeyboardDatePicker,} from '@material-ui/pickers';

const useStyles = makeStyles({
    root: {
        maxWidth: 170,
    },
});

// this function supplies a datepicker object for each ingredient
export default function ExpirationDatePicker(props) {
    const classes = useStyles();
    const [selectedDate, setSelectedDate] = React.useState(props.expiration.get(props.ingredient));

    // update the date view and the expiration map for database update
    const handleDateChange = (date) => {
        setSelectedDate(date);
        props.expiration.set(props.ingredient, date);
    };

    return (
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <Grid className={classes.root} container justify="flex-end">
                <KeyboardDatePicker
                    disableToolbar
                    variant="inline"
                    format="MM/dd/yyyy"
                    label="Expiration Date"
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
