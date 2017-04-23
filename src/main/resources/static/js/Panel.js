import React, { Component, PropTypes } from 'react';
import {ComponentTypes} from "./ComponentTypes";
import {DragSource, DropTarget} from 'react-dnd';
import flow from 'lodash/flow';
import {createNewComponent} from "./drag-and-drop";

let inc = 0;

const source = {
    beginDrag(props) {
        return {
            componentId: ComponentTypes.PANEL
        }
    }
};

const target = {
    drop(props, monitor, component) {
        if (!monitor.didDrop()) {
            component.addChildComponent(monitor.getItemType())
        }
    }
};

function dragSourceCollect(connect, monitor) {
    return {
        connectDragSource: connect.dragSource()
    }
}

function dropTargetCollect(connect, monitor) {
    return {
        connectDropTarget: connect.dropTarget()
    }
}

export class Panel extends Component {
    constructor(props) {
        super(props);
        this.state = {components: []};
    }

    render() {
        const { connectDragSource, connectDropTarget } = this.props;
        const style = this.state.components.length > 0 ? {border: '1px dashed'} : {border: '1px dashed', height: '100px'};
        return connectDragSource(connectDropTarget(
            <section style={style} key={this.props.key}>
                <button>Удалить</button>
                {
                    this.state.components.map((item) => createNewComponent(item, inc++))
                }
            </section>
        ));
    }

    addChildComponent(type) {
        this.setState((prevState) => ({components: prevState.components.concat([type])}));
    }
}

Panel.propTypes = {
    connectDragSource: PropTypes.func.isRequired,
    connectDropTarget: PropTypes.func.isRequired
};

export default flow(
    (DropTarget([
        ComponentTypes.BUTTON,
        ComponentTypes.IMAGE,
        ComponentTypes.PANEL,
        ComponentTypes.TEXT,
        ComponentTypes.TEXTFIELD
    ], target, dropTargetCollect)), (DragSource(ComponentTypes.PANEL, source, dragSourceCollect))
    )(Panel);