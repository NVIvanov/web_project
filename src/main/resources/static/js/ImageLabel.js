import React, {Component} from 'react';
import {DragSource} from 'react-dnd';
import {ComponentTypes} from './editor_components';

export class ImageLabel extends Component {
    constructor(props) {
        super(props);
        this.type = ComponentTypes.IMAGE;
    }

    render() {
        return <section className="col-lg-2 text-center h3">Изображение</section>
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

export default DragSource(ComponentTypes.IMAGE, source, collect)(ImageLabel);