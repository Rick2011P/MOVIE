module.exports = (err, req, res, next) => {
    console.error("ERROR REAL MOVIE:", err);

    const status = err.status || 500;

    res.status(status).json({
        error: status === 500
            ? "Error interno del servidor"
            : err.message
    });
};