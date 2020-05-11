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

        // console.log(props.pantry);

        // const master = props.pantry.keys();
        // console.log(master);

        for (let key of props.pantry.keys()) {
            if (props.pantry.get(key)) {
                ing.push(key);
                exp.push(props.expiration.get(key));
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

        console.log(ing);
        console.log(exp);
        console.log(qnt);
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