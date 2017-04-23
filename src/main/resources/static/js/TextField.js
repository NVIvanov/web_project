import React, { Component, PropTypes } from 'react';
import {ComponentTypes} from './ComponentTypes';
import {DragSource} from 'react-dnd';

export class TextField extends Component {
    render() {
        const style = {border: 'dashed 1px'};
        return <input type="text" style={style} key={this.props.key}/>;
    }
}

const source = {
    beginDrag(props) {
        return {
            componentId: ComponentTypes.TEXTFIELD
        }
    }
};

function collect(connect, monitor) {
    return {
        connectDragSource: connect.dragSource()
    }
}

TextField.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.TEXTFIELD, source, collect)(TextField);