import { Route, Routes } from "react-router-dom";
import CreateCoverLetter from "../components/pages/CoverLetterPage/CreateCoverLetterPage";
import GeneratedCoverLetterPage from "../components/pages/CoverLetterPage/GeneratedCoverLetterPage";
import HomePage from "../components/pages/HomePage";

/**
 * Router component renders a route switch with all available pages
 */

const Router = () => {
  //const { checkRole } = useContext(ActiveUserContext);

  /** navigate to different "home"-locations depending on Role the user have */

  return (
    <Routes>
      <Route path={"/"} element={<HomePage />} />

        <Route
            path={"/createCoverLetter"}
            element={<CreateCoverLetter/>}
        />
        <Route
            path={"/generatedCoverLetter"}
            element={<GeneratedCoverLetterPage/>}
        />

      <Route path="*" element={<div>Not Found</div>} />
    </Routes>
  );
};

export default Router;
