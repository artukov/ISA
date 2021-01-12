import React, { Component } from 'react'
import { Table } from 'react-bootstrap';

export class drugstore extends Component {
    render() {
        return (
            <div>
                <Table striped bordered hover variant="dark">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Drugstore Name</th>
                            <th>Item 1</th>
                            <th>Item 2</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Name</td>
                            <td>item item</td>
                            <td>item item</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Name</td>
                            <td>item item</td>
                            <td>item item</td>
                        </tr>

                    </tbody>
                </Table>
            </div>
        )
    }
}

export default drugstore
