import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';

const useStyles = makeStyles((theme) => ({
    root: {
        '& > *': {
            margin: theme.spacing(1),
        },
    },
}));

export default function UpdatePantry(props) {
    const classes = useStyles();

    const handleUpdate = () => {
        const ing = [];
        const exp = [];
        const qnt = [];

        for (let key of props.pantry.keys()) {
            if (props.pantry.get(key)) {
                ing.push(key);
                let date = props.expiration.get(key);
                if (date == null) {
                    date = new Date();
                }
                exp.push(formatDate(date));
                qnt.push(1);
            }
        }

        // for (let i = 0; i < master.length; i++) {
        //     console.log(props.pantry.get(master[i]));
        //     if (props.pantry.get(master[i])) {
        //         ing.push(master[i]);
        //         exp.push(props.expiration.get(master[i]));
        //         qnt.push(1);
        //     }
        // }

        let pantry = {
            ingredients: ing,
            expirations: exp,
            quantities: qnt,
          };

          const requestOptions = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(pantry),
          };
      
          fetch("http://localhost:4567/save-pantry", requestOptions);
      
         
        

        function formatDate(date) {
            var d = new Date(date),
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