import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

// styles for this functional component
const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
    },
}));

// this function returns an 'Update Pantry' button
// that updates the user pantry in our database
// by using our java SparkServer
export default function UpdateDBPantry(props) {
    const classes = useStyles();

    const handleUpdate = () => {
        // create empty arrays to pass to database
        const ing = [];
        const exp = [];
        const qnt = [];

        // populate arrays with pantry ingredients & expiration dates
        // for now just use default quantity of 1, if we choose to
        // use quantities in our final release, we can modify this code
        for (let key of props.pantry.keys()) {
            if (props.pantry.get(key)) {
                ing.push(key);
                let date = props.expiration.get(key);
                exp.push(formatDate(date));
                qnt.push(1);
            }
        }

        // create pantry object with these arrays
        let pantry = {
            ingredients: ing,
            expirations: exp,
            quantities: qnt,
        };

        // convert object to format suitable to send to database
        const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(pantry),
            credentials: 'include'
        };

        // send pantry to database
        fetch("http://localhost:4567/save-pantry", requestOptions)
            .then(response => response.text())
            .then(data => console.log(data));

        // helper function to change date format to align with database
        function formatDate(date) {
            if (date == null) {
                return null;
            }
            let d = new Date(date),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();

            if (month.length < 2)
                month = '0' + month;
            if (day.length < 2)
                day = '0' + day;

            return [day, month, year].join('-');
        }
    }

    // return update pantry button
    return (
        <div className={classes.root}>
            <Button variant="contained"
                    color="primary"
                    onClick={handleUpdate}>
                Update Pantry
            </Button>
        </div>
    );
}
