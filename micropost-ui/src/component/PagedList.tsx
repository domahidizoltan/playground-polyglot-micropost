import React from 'react';
import './PagedList.css'

interface Item {
    title: string
}

interface Props {
    pages: number,
    items: Item[]
}

export default class PagedList extends React.Component<Props> {
    items: Item[] = [];
    pages: number = 0

    constructor(props: Props) {
        super(props)
        this.items = props.items || []
        this.pages = props.pages || 0
    }

    render() {
        return (
        <div>
            <ul className="list-group striped">
                {this.items.map(item => (
                    <li className="list-group-item"><span>{item.title}</span></li>    
                ))}
            </ul>

            <nav>
                <ul className="pagination">
                    <li className="page-item"><a className="page-link" href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
                    { Array.from(Array(this.pages).keys()).map(i => (
                        <li className="page-item"><a className="page-link" href="#">{i}</a></li>
                    )) }
                    <li className="page-item"><a className="page-link" href="#" aria-label="Next"><span aria-hidden="true">»</span></a></li>
                </ul>
            </nav>
        </div>
        )
    }
}
