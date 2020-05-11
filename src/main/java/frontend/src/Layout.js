import React from 'react';

import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';

import SimpleExpansionPanel from './pantry';
import RecipeComponent from "./generateRecipes";
import Logo from "./logo"

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
        // backgroundColor: "aliceblue",
        backgroundImage: `url(https://thepaintpeople.com/wp-content/uploads/2015/09/prepare-bare-wood-staining.jpg)`,
        backgroundPosition: 'center',
        backgroundSize: '100%',
        backgroundRepeat: 'repeat-y',
    },

    header: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
        // background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
        // background: 'linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c)',
    },
    pantry: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
        opacity: '85%',
        height: '100%',
        // backgroundImage: `url(https://thepaintpeople.com/wp-content/uploads/2015/09/prepare-bare-wood-staining.jpg)`,
        // backgroundPosition: 'center',
        // backgroundSize: 'cover',
        // backgroundRepeat: 'no-repeat',
    },
    recipes: {
        padding: theme.spacing(2),
        opacity: '85%',
        // textAlign: 'left',
        color: theme.palette.text.secondary,
        height: '100%',
    },
    logo: {
        display: 'inline',
        float: 'left',
    },
    login: {
        display: 'inline',
        float: 'right',
    }
}));

export default function Layout() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <Grid container spacing={3} justify="flex-end">
                <Grid item xs={12}>
                    <Paper className={classes.header}>
                        <Logo />
                    </Paper>
                </Grid>
                <Grid item xs={5} alignItems="stretch">
                    <Paper className={classes.pantry}>
                        <h2>Pantry</h2>
                        <SimpleExpansionPanel />
                    </Paper>
                </Grid>
                <Grid item xs={7}>
                    {/*<Paper className={classes.paper}>Filters</Paper>*/}
                    <Paper className={classes.recipes} alignItems="stretch">
                        <RecipeComponent />
                    </Paper>
                </Grid>
                <Grid item xs={12}> </Grid>
            </Grid>
        </div>
    );
}