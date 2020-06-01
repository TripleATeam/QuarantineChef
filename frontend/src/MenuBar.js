import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Logo from "./Logo";
import SignIn from './SignIn.js';

const useStyles = makeStyles(() => ({
    root: {
        flexGrow: 1,
    },
}));

export default function MenuBar(props) {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    <Logo />
                    < SignIn handleUserUpdate={props.handleUserUpdate} />
                </Toolbar>
            </AppBar>
        </div>
    );
}