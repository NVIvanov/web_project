import React, { Component, PropTypes } from 'react';
import { DropTarget } from 'react-dnd';
import { ComponentTypes } from './ComponentTypes';
import {createNewComponent} from "./drag-and-drop";

let inc = 0;

const editorTarget = {
    drop(props, monitor, component) {
        if (!monitor.didDrop()) {
            component.addChildComponent(monitor.getItemType())
        }
    }
};

function collect(connect, monitor) {
    return {
        connectDropTarget: connect.dropTarget()
    };
}

class EditorField extends Component {
    constructor(props) {
        super(props);
        this.state = {components: []};
    }

    render() {
        const style = {border: '1px dashed', width: '80%', height: '500px'};
        return this.props.connectDropTarget(<section style={style}>
            {
                this.state.components.map((item) => createNewComponent(item, inc++))
            }
        </section>);
    }

    addChildComponent(type) {
        this.setState((prevState) => ({components: prevState.components.concat([type])}));
    }
}

EditorField.PropTypes = {
    connectDropTarget: PropTypes.func.isRequired
};

export default DropTarget(
    [ ComponentTypes.BUTTON,
    ComponentTypes.IMAGE,
    ComponentTypes.PANEL,
    ComponentTypes.TEXT,
    ComponentTypes.TEXTFIELD ],
editorTarget, collect)(EditorField);