import React from 'react';

import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';

// styles for this functional component
const useStyles = makeStyles({
    root: {
        textAlign: 'left',
        width: '100%',
        maxWidth: 500,
    },
});

// this function returns our web app logo
// for the time being, it is simply text
// TODO: create and display web app logo
export default function Logo() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Typography variant="h3" gutterBottom>
                Quarantine Chef
            </Typography>
        </div>
    );
}
