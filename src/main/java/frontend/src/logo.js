import React from 'react';

import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
// import logo from './logo.svg';

const useStyles = makeStyles({
    root: {
        textAlign: 'left',
        width: '100%',
        maxWidth: 500,
    },
});

export default function Logo() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Typography variant="h3" gutterBottom>
                {/*<img src={logo} width="75" height="75" alt={'logo'} />*/}
                Quarantine Chef
            </Typography>
        </div>
    );
}
