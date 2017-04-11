import React, {Component} from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './editor_components';

export class ButtonLabel extends Component {
    constructor(props) {
        super(props);
        this.type = ComponentTypes.BUTTON;
    }

    render() {
        return <section className="col-lg-2 text-center h3">Кнопка</section>
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

export default DragSource(ComponentTypes.BUTTON, source, collect)(ButtonLabel);