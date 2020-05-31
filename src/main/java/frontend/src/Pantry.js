import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import ExpansionPanel from '@material-ui/core/ExpansionPanel';
import ExpansionPanelSummary from '@material-ui/core/ExpansionPanelSummary';
import ExpansionPanelDetails from '@material-ui/core/ExpansionPanelDetails';
import Typography from '@material-ui/core/Typography';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import IngredientCheckboxList from './Ingredients';

// styles for this functional component
const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
        justifyContent: "center",
        justifyItems: "center",
    },
    heading: {
        fontSize: theme.typography.pxToRem(15),
        fontWeight: theme.typography.fontWeightRegular,
    },
    type: {
        width: '100%',
        maxWidth: 500,
    },
}));

// helper function to create a 0-based index array with the number
// of ingredient types
function enumerate(list) {
    let nums = [];
    for (let i = 0; i < list.length; i++) {
        nums.push(i);
    }
    return nums;
}

// this function returns an expansion panel for each ingredient type
export default function IngredientTypeExpansionPanel(props) {
    const classes = useStyles();
    
    return (
        <div className={classes.root}>
            {enumerate(props.types).map((value) => {
                const labelId = `checkbox-list-label-${value}`;
                return (
                    <ExpansionPanel className={classes.type}
                                    key={labelId} TransitionProps={{ mountOnEnter: true }}>
                        <ExpansionPanelSummary
                            expandIcon={<ExpandMoreIcon />}
                            aria-controls={`${labelId}`}
                            id={`${labelId}`}
                        >
                            <Typography className={classes.heading}>{props.types[value]}</Typography>
                        </ExpansionPanelSummary>
                        <ExpansionPanelDetails>
                            <IngredientCheckboxList type={props.types[value]}
                                                    ingredients={props.ingredients.get(props.types[value])}
                                                    pantry={props.pantry}
                                                    expiration={props.expiration}/>
                        </ExpansionPanelDetails>
                    </ExpansionPanel>
                );
            })}
        </div>
    );
}
