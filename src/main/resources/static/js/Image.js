import React, { Component, PropTypes } from 'react';
import {ComponentTypes} from "./ComponentTypes";
import {DragSource} from 'react-dnd';

export class Image extends Component {
    render() {
        const style = {border: 'dashed 1px', height: '100px', width: '100px'};
        return <img style={style} key={this.props.key} src=""/>;
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
    }
}

Image.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.IMAGE, source, collect)(Image);