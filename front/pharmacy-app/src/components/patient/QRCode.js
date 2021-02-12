import React, { useState, useEffect } from 'react';
import { Button, Form } from 'react-bootstrap';

const QRCode = () => {
    return (<div>
            <Form>
    <Form.Group>
                <Form.File onChange={(e) => { 
                    console.log(e.target.files[0]);
                }} id="exampleFormControlFileqrCode" label="Choose QR Code" />
    </Form.Group>
    </Form>
    </div>
    );
}
 
export default QRCode;