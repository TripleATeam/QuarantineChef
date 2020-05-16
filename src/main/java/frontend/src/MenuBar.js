import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
// import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import Logo from "./Logo";

// import { CardMedia } from '@material-ui/core';
//
// import logo from './logo.png';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    // menuButton: {
    //     marginRight: theme.spacing(2),
    // },
    // title: {
    //     flexGrow: 1,
    //     textAlign: "start",
    // },
    // media: {
    //     height: 50,
    // },
}));

export default function MenuBar() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <AppBar position="static">
                <Toolbar>
                    {/*<CardMedia className={classes.media}*/}
                    {/*           image={logo}*/}
                    {/*           title="Logo"*/}
                    {/*/>*/}
                    <Logo />
                    {/*<Typography variant="h6" className={classes.title}>*/}
                    {/*    QuarantineChef*/}
                    {/*</Typography>*/}
                    <Button color="inherit">Login</Button>
                </Toolbar>
            </AppBar>
        </div>
    );
}