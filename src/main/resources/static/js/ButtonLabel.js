import React, { Component, PropTypes } from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './ComponentTypes';

class ButtonLabel extends Component {
    render() {
        return this.props.connectDragSource(<section className={"col-lg-2 text-center h3"}>Кнопка</section>);
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

ButtonLabel.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.BUTTON, source, collect)(ButtonLabel);