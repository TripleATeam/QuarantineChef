import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import Checkbox from '@material-ui/core/Checkbox';
import ExpirationDatePicker from './Expiration.js';

// styles for this functional component
const useStyles = makeStyles({
    root: {
        width: '100%',
    },
    ingredient: {
        height: 'auto !important',
    },
});

// helper function to create a 0-based index array with the number
// of ingredients in each ingredient type
function enumerate(list) {
  let nums = [];
  for (let i = 0; i < list.length; i++) {
      nums.push(i);
  }
  return nums;
}

export default function IngredientCheckboxList(props) {
    const classes = useStyles();
    const [checked, setChecked] = React.useState([]);

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (currentIndex === -1) {
            newChecked.push(value);
        } else {
            newChecked.splice(currentIndex);
        }
        setChecked(newChecked);

        props.pantry.set(value, !props.pantry.get(value));
    };

    return (
        <List className={classes.root}>
            {enumerate(props.ingredients).map((value) => {
                if (props.pantry.get(props.ingredients[value])) {
                    checked.push(props.ingredients[value]);
                }
                const labelId = `checkbox-list-label-${props.ingredients[value]}`;
                return (
                    <ListItem className={classes.ingredient}
                              key={props.ingredients[value]}
                              role={undefined}
                              dense
                              >
                        <ListItemIcon>
                            <Checkbox
                                edge="start"
                                checked={checked.indexOf(props.ingredients[value]) !== -1}
                                color={'primary'}
                                disableRipple
                                inputProps={{ 'aria-labelledby': labelId }}
                                button onClick={handleToggle(props.ingredients[value])}
                            />
                        </ListItemIcon>
                        <ListItemText id={labelId} primary={props.ingredients[value]} button onClick={handleToggle(props.ingredients[value])}/>
                        <ExpirationDatePicker ingredient={props.ingredients[value]}
                                              expiration={props.expiration}/>
                    </ListItem>
                );
            })}
        </List>
    );
}
