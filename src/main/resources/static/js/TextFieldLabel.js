import React, { Component, PropTypes } from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './ComponentTypes';

class TextFieldLabel extends Component {
    render() {
        const { connectDragSource } = this.props;
        return connectDragSource(<section className={"col-lg-2 text-center h3"}>Поле ввода</section>);
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
    };
}

TextFieldLabel.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.TEXTFIELD, source, collect)(TextFieldLabel);