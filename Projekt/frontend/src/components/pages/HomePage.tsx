import { Box } from '@mui/system';
import {useNavigate} from "react-router-dom";
import {useState} from "react";
import './ClickableText.css';
import CreateIcon from '@mui/icons-material/Create';

export default function HomePage() {
  const history = useNavigate();
  const [expand, setExpand] = useState(false);

  const handleClick = () => {
    setExpand(true);
    setTimeout(() => {
      history('/createCoverLetter');
    }, 300); // Change the duration as needed

  };
  return (
      <Box onClick={handleClick} style={{alignItems:"center", height: "100%"}}>
      <div className={`icon-container ${expand ? 'expand' : ''}`}>
        <CreateIcon sx={{fontSize:"10rem", color: "#05386B"}}/>
      </div>
          <h3 style={{color: "#05386B", fontSize: "3rem", marginTop: "12rem", marginLeft:"26%"}}>Click the pencil to create a cover letter!</h3>
      </Box>
  );
}
