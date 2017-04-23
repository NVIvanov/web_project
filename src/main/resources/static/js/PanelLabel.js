import React, { Component, PropTypes } from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './ComponentTypes';

class PanelLabel extends Component {
    render() {
        const { connectDragSource } = this.props;
        return connectDragSource(<section className={"col-lg-2 text-center h3"} key={this.props.key}>Панель</section>);
    }
}

const source = {
    beginDrag(props) {
        return {
            componentId: ComponentTypes.PANEL
        }
    }
};

function collect(connect, monitor) {
    return {
        connectDragSource: connect.dragSource()
    };
}

PanelLabel.propTypes = {
    connectDragSource: PropTypes.func.isRequired
};

export default DragSource(ComponentTypes.PANEL, source, collect)(PanelLabel);