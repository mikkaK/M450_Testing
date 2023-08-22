import {FormControl, Grid, TextField} from "@mui/material";


const GridMapping = ({formFields, formik}: any) => {
    return (
        <>
            {formFields.map((section: any) => (
            <>
                <Grid item xs={3} sx={{ marginBottom: 5 }}>
                    <h3>{section.label}</h3>
                </Grid>
                {section.fields.map((field: any) => (
                    <Grid item xs={3} sx={{ marginBottom: 5 }}>
                        <FormControl>
                            <TextField
                                value={formik.values[field.name]}
                                label={field.label}
                                name={field.name}
                                type={field.type || 'text'}
                                onChange={formik.handleChange}
                            />
                        </FormControl>
                    </Grid>
                ))}
            </>
            ))}
        </>
    );
};

export default GridMapping;
