import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Checkbox from '@material-ui/core/Checkbox';
import MaterialUIPickers from './expiration.js';

const useStyles = makeStyles((theme) => ({
    root: {
        width: '100%',
    },
}));

function enumerate(list) {
  let nums = [];
  for (let i = 0; i < list.length; i++) {
      nums.push(i);
  }
  return nums;
}

export default function CheckboxList(props) {
    const classes = useStyles();
    const [checked, setChecked] = React.useState([]);

    const handleToggle = (value) => () => {
        const currentIndex = checked.indexOf(value);
        const newChecked = [...checked];

        if (!props.pantry.get(props.ingredients[value])) {
            newChecked.push(value);
            props.pantry.set(props.ingredients[value], true);
        } else {
            newChecked.splice(currentIndex);
            props.pantry.set(props.ingredients[value], false);
        }

        setChecked(newChecked);
    };

    return (
        <List className={classes.root}>
            {enumerate(props.ingredients).map((value) => {
                if (props.pantry.get(props.ingredients[value])) {
                    checked.push(value);
                }
                const labelId = `checkbox-list-label-${value}`;
                return (
                    <ListItem key={value} role={undefined} dense button onClick={handleToggle(value)}>
                        <ListItemIcon>
                            <Checkbox
                                edge="start"
                                checked={checked.indexOf(value) !== -1}
                                // tabIndex={-1}
                                disableRipple
                                inputProps={{ 'aria-labelledby': props.ingredients[value] }}
                            />
                        </ListItemIcon>
                        <ListItemText id={labelId} primary={props.ingredients[value]} />
                        <ListItemSecondaryAction>
                            <MaterialUIPickers ingredient={props.ingredients[value]}
                                               expiration={props.expiration}/>
                        </ListItemSecondaryAction>
                    </ListItem>
                );
            })}
        </List>
    );
}
