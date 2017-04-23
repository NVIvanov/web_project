import React, { Component, PropTypes } from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './ComponentTypes';

class ImageLabel extends Component {
    render() {
        const { connectDragSource } = this.props;
        return connectDragSource(<section className={"col-lg-2 text-center h3"}>Изображение</section>);
    }
}

const source = {
    beginDrag(props) {
        return {
            componentId: ComponentTypes.IMAGE
        }
    }
};

function collect(connect, monitor) {
    return {
        connectDragSource: connect.dragSource()
    };
}

ImageLabel.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.IMAGE, source, collect)(ImageLabel);