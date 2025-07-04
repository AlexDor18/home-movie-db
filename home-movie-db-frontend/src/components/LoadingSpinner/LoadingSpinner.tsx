const LoadingSpinner: React.FC = () => {
    return (
        <div data-testid="loading-spinner" className="flex justify-center">
            <div className="animate-spin inline-block w-8 h-8 border-4 rounded-full border-t-transparent border-b-transparent">
            </div>
        </div>
    );
};

export default LoadingSpinner;
