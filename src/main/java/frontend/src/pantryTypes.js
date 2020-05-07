import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import CheckboxList from './ingredients';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        maxWidth: 500,
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        fontWeight: theme.typography.fontWeightRegular,
    },
}));

function enumerate(list) {
    let nums = [];
    for (let i = 0; i < list.length; i++) {
        nums.push(i);
    }
    return nums;
}

const ingredientTypes = ['Dairy','Meat','Grains','Vegetables'];

export default function SimpleExpansionPanel() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            {enumerate(ingredientTypes).map((value) => {
                const labelId = `checkbox-list-label-${value}`;
                return (
                    <ExpansionPanel>
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls={`${labelId}`}
                            id={`${labelId}`}
                        >
                            <Typography className={classes.heading}>{ingredientTypes[value]}</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <CheckboxList />
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                );
            })}
        </div>
    );
}
