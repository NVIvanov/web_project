import React, { PropTypes, Component } from 'react';
import {ComponentTypes} from "./ComponentTypes";
import {DragSource} from 'react-dnd';

class Button extends Component {
    render() {
        const style = {border: 'dashed 1px'};
        return this.props.connectDragSource(<button style={style} key={this.props.key}>{this.props.text}</button>);
    }
}

const source = {
    beginDrag(props) {
        return {
            componentId: ComponentTypes.BUTTON
        }
    }
};

function collect(connect, monitor) {
    return {
        connectDragSource: connect.dragSource()
    }
}

Button.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.BUTTON, source, collect)(Button);