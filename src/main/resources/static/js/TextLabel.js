import React, { Component, PropTypes } from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './ComponentTypes';

class TextLabel extends Component {
    render() {
        const { connectDragSource } = this.props;
        return connectDragSource(<section className={"col-lg-2 text-center h3"}>Текст</section>);
    }
}

const source = {
    beginDrag(props) {
        return {
            componentId: ComponentTypes.TEXT
        }
    }
};

function collect(connect, monitor) {
    return {
        connectDragSource: connect.dragSource()
    };
}

TextLabel.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.TEXT, source, collect)(TextLabel);