import * as React from 'react';
import Button from '@mui/joy/Button';
import Input from '@mui/joy/Input';
import Select from '@mui/joy/Select';
import Option from '@mui/joy/Option';
import Switch from '@mui/joy/Switch';
import Avatar from '@mui/joy/Avatar';
import Box from '@mui/joy/Box';
import Sidebar from './Sidebar';

export default function Component14() {
    const [checked, setChecked] = React.useState(false);
    
    const handleChange = (event, newValue) => {
    alert(`You chose "${newValue}"`);
  };
  
  return (<>
    <p>소문자 마크업 : html, 대문자 마크업 : Component</p>
    <p>마크업 속성(props)</p>

    <p>Button</p>
    <Button variant="solid">Hello world</Button>
    
    <p>Input</p>
    <Input placeholder="Type in here…" />

    <p>Select</p>
    <Select defaultValue="dog" onChange={handleChange}>
        <Option value="dog">Dog</Option>
        <Option value="cat">Cat</Option>
        <Option value="fish">Fish</Option>
        <Option value="bird">Bird</Option>
    </Select>

    <p>Switch</p>
    <Switch
      checked={checked}
      onChange={(event) => setChecked(event.target.checked)}
    />

    <p>Avatar</p>
    {/* {{}} : CSS 적용 (카멜 표기법으로 작성해야 함) */}
    {/* Box : <div>와 같은 유형 */}
    <Box sx={{ display: 'flex', gap: 2, backgroundColor: 'black' }}>
      <Avatar />
      <Avatar>JG</Avatar>
      <Avatar alt="Remy Sharp" src="/static/images/avatar/1.jpg" />
    </Box>

    <p>List</p>
    <Sidebar />
  </>)
}