import React, {Component} from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './editor_components';

export class TextFieldLabel extends Component {
    constructor(props) {
        super(props);
        this.type = ComponentTypes.TEXTFIELD;
    }
    render() {
        return <section className="col-lg-2 text-center h3">Поле ввода</section>
    }
}

const source = {
    beginDrag(props) {
        return {
            componentId: props.type
        }
    }
};

function collect(connect, monitor) {
    return {};
}

export default DragSource(ComponentTypes.TEXTFIELD, source, collect)(TextLabel);