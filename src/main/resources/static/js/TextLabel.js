import React, {Component} from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './editor_components';

export class TextLabel extends Component {
    constructor(props) {
        super(props);
        this.type = ComponentTypes.TEXT;
    }

    render() {
        return <section className="col-lg-2 text-center h3">Текст</section>
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

export default DragSource(ComponentTypes.TEXT, source, collect)(TextLabel);