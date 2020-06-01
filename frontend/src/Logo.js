import React from 'react';

import { makeStyles } from '@material-ui/core/styles';
import { CardMedia } from '@material-ui/core';

import logo from './logo.png';

// styles for this functional component
const useStyles = makeStyles({
    root: {
        flexGrow: 1,
    },
    media: {
        height: 75,
        width: 304,
    },
});

// this function returns our web app logo
// for the time being, it is simply text
export default function Logo() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <a href={'/'}>
                <CardMedia className={classes.media}
                           component="img"
                           alt="QuarantineChef"
                           image={logo}
                           title="Logo"
                />
            </a>
        </div>
    );
}
