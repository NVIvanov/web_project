import React, { Component, PropTypes } from 'react';
import {ComponentTypes} from './ComponentTypes';
import {DragSource} from 'react-dnd';

export class Text extends Component {
    render() {
        const style = {border: 'dashed 1px'};
        return <p style={style} key={this.props.key}>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
            Fusce interdum semper metus tempus scelerisque. Mauris ex nisi, facilisis nec ullamcorper eget,
            tempus interdum libero.
            <button>Удалить</button>
        </p>;
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
    }
}

Text.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.TEXT, source, collect)(Text);